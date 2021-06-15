package com.example.shop.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.example.shop.models.BuyOrder;
import com.example.shop.models.CartItem;
import com.example.shop.models.Customer;
import com.example.shop.models.OrderStatus;
import com.example.shop.models.PaymentStatus;
import com.example.shop.models.PaymentType;
import com.example.shop.models.Product;
import com.example.shop.models.request.RequestToken;
import com.example.shop.models.request.ResponseBuyOrder;
import com.example.shop.models.request.ResponseBuyOrderPage;
import com.example.shop.repository.BuyOrderRepository;
import com.example.shop.repository.CartItemRepository;
import com.example.shop.repository.CustomerRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.util.JwtUtil;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class BuyOrderService {
	
	private BuyOrderRepository buyOrderRepository;
	@Autowired 
	private CartItemRepository cartItemRepository;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Autowired
	public BuyOrderService(BuyOrderRepository buyOrderRepository) {
		this.buyOrderRepository = buyOrderRepository;
	}
	public Slice<BuyOrder> getAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findAll(pageable);
	}
	public Slice<BuyOrder> getByCustomerId(long customerId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findByCustomerId(customerId, pageable);
	}
	public Slice<BuyOrder> getBySellerId(long sellerId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findBySellerId(sellerId, pageable);
	}
	public Slice<BuyOrder> getByProductId(long productId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findByProductId(productId, pageable);
	}
	public Slice<BuyOrder> getByPaymentType(String paymentType, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		try	{
			PaymentType value = PaymentType.valueOf(paymentType.toUpperCase());
			return buyOrderRepository.findByPaymentType(value, pageable);
		}
		catch(Exception exception) {
			return buyOrderRepository.findByPaymentType(null, pageable);
		}
	}
	public Slice<BuyOrder> getByOrderStatus(String orderStatus, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		try {
			OrderStatus value = OrderStatus.valueOf(orderStatus.toUpperCase());
			return buyOrderRepository.findByOrderStatus(value, pageable);
		}
		catch(Exception exception) {
			return buyOrderRepository.findByOrderStatus(null, pageable);
		}
	}
	public Slice<BuyOrder> getByShippingCity(String shippingCity, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findByShippingCityIgnoreCase(shippingCity, pageable);
	}
	public Slice<BuyOrder> getByShippingState(String shippingState, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findByShippingStateIgnoreCase(shippingState, pageable);
	}
	public Slice<BuyOrder> getByShippingAddress(String shippingAddress, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findByShippingAddressIgnoreCase(shippingAddress, pageable);
	}
	public Slice<BuyOrder> getByPincode(long pincode, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findByPincode(pincode, pageable);
	}
	public String createOrder(Map<String, Object> map) throws Exception {
		System.out.println(map);
		final String keyId = "rzp_test_NUfcQVU44SeBMy";
		final String keySecret = "OH7nkScaCXkQsZfumrmt5GgM";
		RazorpayClient razorpayClient = new RazorpayClient(keyId, keySecret);
		JSONObject options = new JSONObject();
		int amount = Integer.parseInt(map.get("amount").toString());
		Map<String,Object> attributes = (Map)map.get("attributes");
		String token = (String)attributes.get("token");
		// extract other info from fields 
		String username = jwtUtil.extractUsername(token);
		System.out.println("username:"+ username);
		Customer customer = customerRepository.findByEmailIgnoreCase(username);
		if(customer == null) {
			throw new IllegalStateException("invalid username");
		}
		List<CartItem> cartItems = cartItemRepository.findByCustomerId(customer.getId());
		List<BuyOrder> orderList = new ArrayList<>();
		int dbAmt = 0;
		for(CartItem cartItem: cartItems) {
			long customerId = cartItem.getCustomerId();
			long productId = cartItem.getProductsId();
			Optional<Product> product = productRepository.findById(productId);
			if(product.isEmpty()) {
				throw new IllegalStateException("Product does not exists:"+ productId);
			}
			BuyOrder buyOrder = new BuyOrder();
			buyOrder.setId(UUID.randomUUID().toString());
			buyOrder.setCustomerId(customerId);
			//buyOrder.setSellerId(product.get().getSellerId());
			buyOrder.setSellerId(12345);
			buyOrder.setProductId(productId);
			buyOrder.setOrderDate(LocalDate.now());
			buyOrder.setOrderShipDate(LocalDate.now().plusDays(4));
			buyOrder.setPaymentStatus(PaymentStatus.PENDING);
			buyOrder.setPaymentType(PaymentType.COD);
			buyOrder.setOrderStatus(OrderStatus.PROCESSING);
			buyOrder.setShippingCity("test_city");
			buyOrder.setShippingState("test_state");
			buyOrder.setShippingAddress("test_address");
			buyOrder.setPincode(123456);
			System.out.println(buyOrder);
			//dbAmt += Integer.parseInt((String)product.get().getAttribute("price"));
			orderList.add(buyOrder);
		}
		for(BuyOrder order: orderList) {
			buyOrderRepository.save(order);
			System.out.println(order.getId() + ":saved");
		}
		options.put("amount", amount*100);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		Order order = razorpayClient.Orders.create(options);
			
		
		String responseString = "{\"order\":" + order.toString() +  
								", \"order_id\": " + orderList.toString()+ "}";
		System.out.println(responseString);
		return order.toString();
	}
	public Page<BuyOrder> getHistory(RequestToken request, int page, int size) {
		String username = jwtUtil.extractUsername(request.getToken());
		Customer customer = this.customerRepository.findByEmailIgnoreCase(username);
		if(customer == null) {
			throw new IllegalStateException("User doesn't exists");
		}
		Pageable pageable = PageRequest.of(page, size);
		Page<BuyOrder> orders = this.buyOrderRepository.findByCustomerId(customer.getId(), pageable);
		return orders;
	}
	public Optional<BuyOrder> getByOrderId(String orderId) {
		return this.buyOrderRepository.findById(orderId);
	}
	
	public ResponseBuyOrderPage getAllOrder(long sellerId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<BuyOrder> orders = this.buyOrderRepository.findBySellerId(sellerId, pageable);
		List<BuyOrder> list = orders.getContent();
		ResponseBuyOrderPage response = new ResponseBuyOrderPage();
		response.setOffset(orders.getPageable().getOffset());
		response.setPageNumber(orders.getPageable().getPageNumber());
		response.setPageSize(orders.getPageable().getPageSize());
		response.setSize(orders.getSize());
		response.setTotalElements(orders.getTotalElements());
		response.setTotalPages(orders.getTotalPages());
		List<ResponseBuyOrder> resOrder = new ArrayList<>();
		for(BuyOrder bo : list) {
			ResponseBuyOrder or =new ResponseBuyOrder();
			or.setOrderDate(bo.getOrderDate());
			or.setId(bo.getId());
			or.setOrderShipDate(bo.getOrderShipDate());
			or.setOrderStatus(bo.getOrderStatus());
			or.setPaymentStatus(bo.getPaymentStatus());
			or.setPaymentType(bo.getPaymentType());
			or.setPincode(or.getPincode());
			or.setSellerId(bo.getSellerId());
			or.setShippingAddress(bo.getShippingAddress());
			or.setShippingCity(bo.getShippingCity());
			or.setShippingState(bo.getShippingState());
			Optional<Customer> customer = this.customerRepository.findById(bo.getCustomerId());
			if(customer.isEmpty()) {
				continue;
			}
			Optional<Product> product = this.productRepository.findById(bo.getProductId());
			if(product.isEmpty()) {
				continue;
			}
			or.setCustomer(customer.get());
			or.setProduct(product.get());
			resOrder.add(or);
		}
		response.setContent(resOrder);
		return response;
	}
	public Map<Integer, Long> getYearlyRevenue(long sellerId, int startYear, int endYear) {
		Map<Integer, Long> rev = new HashMap<>();
		if(endYear < startYear) {
			throw new IllegalArgumentException("invalid range of dates");
		}
		for(int i =startYear;i<=endYear;++i) {
			rev.put(i, 0l);
		}
		List<BuyOrder> orders = this.buyOrderRepository.findBySellerId(sellerId);
		//Map<Integer, List<BuyOrder>> productId = 
		List<Long> productIds = orders.stream().filter(order->
			 order.getOrderDate().getYear() >= startYear
					&& order.getOrderDate().getYear() <= endYear
		).map(order->order.getProductId()).collect(Collectors.toList());
		List<Product> products = this.productRepository.findByIdIn(productIds);
		
		 Map<Object, List<BuyOrder>> map = orders.stream().filter((order)->{
			return order.getOrderDate().getYear() >= startYear
					&& order.getOrderDate().getYear() <= endYear;
		}).collect(
				Collectors.groupingBy(order->order.getOrderDate().getYear())
        );
		  for (Map.Entry<Object,List<BuyOrder>> entry : map.entrySet()) {
			  int year = Integer.parseInt(entry.getKey().toString());
			  List<BuyOrder> orderListYearWise = entry.getValue();
			 long amt= 0;
			  for(BuyOrder orderYearWise: orderListYearWise) {
				  long productId = orderYearWise.getProductId();
				  List<Product> item = products.stream().filter(product->product.getId()==productId)
				  .collect(Collectors.toList());
				  if(item.size() == 0)
					  continue;
				  Product p = item.get(0);
				  Map<String, Object> attributes = p.getAttributes();
				  long price = Long.parseLong(attributes.get("price").toString());	
				  amt = rev.get(entry.getKey());
				  int qty = 1;// qty is 1 later each order may have different qty of product
				  amt += price*qty;
				 rev.put(year, amt);
			  }
		  }
		return rev;
	}
	public Map<String, Long> getMonthlyRevenue(long sellerId, int year, String startMonthString, String endMonthString) {
		Month startMonth = null;
		Month endMonth =null;
		try {
			startMonth = Month.valueOf(startMonthString.toUpperCase());
			endMonth = Month.valueOf(endMonthString.toUpperCase());
		}
		catch(Exception exception) {
			throw new IllegalArgumentException("Invalid month specified");
		}
		Map<String, Long> rev = new HashMap<>();
		if(startMonth.getValue() > endMonth.getValue()) {
			throw new IllegalArgumentException("Invalid order of months");
		}
		for(int i =startMonth.getValue();i<=endMonth.getValue();++i) {
			rev.put(Month.of(i).toString(), 0l);
		}
		System.out.println(rev);
		List<BuyOrder> orders = this.buyOrderRepository.findBySellerId(sellerId);
		//Map<Integer, List<BuyOrder>> productId = 
		List<Long> productIds = orders.stream().filter((order)->{
			return order.getOrderDate().getYear() == year;		
		}).map(order->order.getProductId()).collect(Collectors.toList());
		List<Product> products = this.productRepository.findByIdIn(productIds);
		
		 Map<Object, List<BuyOrder>> map = orders.stream().filter((order)->{
			return order.getOrderDate().getYear() ==year;
		}).collect(
				Collectors.groupingBy(order->order.getOrderDate().getMonth().toString())
        );
		  for (Map.Entry<Object,List<BuyOrder>> entry : map.entrySet()) {
			  Month month = Month.valueOf(entry.getKey().toString());
			  List<BuyOrder> orderListMonthWise = entry.getValue();
			 long amt= 0;
			  for(BuyOrder orderMonthWise: orderListMonthWise) {
				  try {
				  long productId = orderMonthWise.getProductId();
				  List<Product> item = products.stream().filter(product->product.getId()==productId)
				  .collect(Collectors.toList());
				  if(item.size() == 0)
					  continue;
				  Product p = item.get(0);
				  Map<String, Object> attributes = p.getAttributes();
				  Object priceObject = attributes.get("price");
				  if(priceObject == null) {
					  continue;
				  }
				  long price = Long.parseLong(priceObject.toString());	
				  amt = rev.get(entry.getKey().toString());
				  int qty = 1;// qty is 1 later each product may have different qty
				  amt += price*qty;
				 rev.put(month.toString(), amt);
				  }
				  catch(NullPointerException nullPointerException) {
					
				  }
			  }
		  }
		return rev;
	}
}

