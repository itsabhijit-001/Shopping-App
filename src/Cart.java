import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Cart {
	public static HashMap<Integer, Product> prodList = new HashMap<>();
	public static HashMap<Integer, Product> cartList = new HashMap<>();
	static float totalAmount = 0;

	public static void main(String[] args) {

		String cartId;
		Scanner scn = new Scanner(System.in);
		String cName, cEmailId, pName, pBrand;
		int pQuantity, pId = 1001;
		float pPrice;
		long cPhoneNumber;
		Customer c1 = new Customer();
		System.out.println("Enter customer name ");
		cName = scn.nextLine();
		System.out.println("Enter customer Email id ");
		cEmailId = scn.next();
		System.out.println("Enter customer phone number ");
		cPhoneNumber = scn.nextLong();
		System.out.println("Customer data added !!");
		cartId = cEmailId;
		c1.setCustomerDetails(cName, cEmailId, cPhoneNumber);
		String val;
		System.out.println();
		System.out.println("**** Add products to inventory ****");

		do {
			Product p = new Product();
			System.out.println("Enter product name ");
			pName = scn.next();
			System.out.println("Enter product brand name ");
			pBrand = scn.next();
			System.out.println("Enter product quantity ");
			pQuantity = scn.nextInt();
			System.out.println("Enter product price ");
			pPrice = scn.nextFloat();
			p.addNewProduct(pName, pBrand, pQuantity, pPrice, pId);
			prodList.put(pId, p);
			System.out.println("Product data added !!");
			System.out.println("Do you want to add more product to inventory? ('yes' or 'no') ");
			val = scn.next().toLowerCase();
			pId += 1;

		} while (val.equals("yes"));

		System.out.println();
		System.out.println("Do you want to add product to cart? ('y' for yes)");
		String val1 = scn.next().toLowerCase();
		if (val1.equals("y")) {
			do {
				System.out.println("Want to see inventory? ('y' for yes)");
				if (scn.next().toLowerCase().equals("y")) {
					listRemainingInventory();
				}
				System.out.println("Enter product id ");
				int productId;
				productId = scn.nextInt();
				System.out.println("How many products you wanted to add? ");
				int productQuantity;
				productQuantity = scn.nextInt();
				addToCart(productId, productQuantity);
				System.out.println("want to add more? ('y' for yes)");
			} while (scn.next().toLowerCase().equals("y"));
			System.out.println("Total price of items in cart : " + totalAmount);
			System.out.println();
		}

		System.out.println("Do you want to remove product from cart? ('y' for yes)");
		String val2 = scn.next().toLowerCase();
		if (val2.equals("y")) {
			do {
				System.out.println("Want to see inventory? ('y' for yes)");
				if (scn.next().toLowerCase().equals("y")) {
					listRemainingInventory();
				}
				System.out.println("Enter product id ");
				int productId;
				productId = scn.nextInt();
				System.out.println("How many products you wanted to remove? ");
				int productQuantity;
				productQuantity = scn.nextInt();
				removeFromCart(productId, productQuantity);
				System.out.println("want to remove more? ('y' for yes)");
			} while (scn.next().toLowerCase().equals("y"));
			System.out.println("Total price of items in cart : " + totalAmount);
			System.out.println();
		}
		listRemainingInventory();
		listOfItemInCart();
		System.out.println("Total price of items in cart : " + totalAmount);

		scn.close();

	}

	public static void addToCart(int prodId, int prodQuantity) {
		if (prodList.containsKey(prodId)) {

			Product cProduct = prodList.get(prodId);
			if (cProduct.pQuantity < prodQuantity) {
				System.out.println("Sorry not enough quantity available !!!");
				System.out.println("Only " + cProduct.pQuantity + " " + cProduct.pName + " are available.");
			} else {
				cProduct.pQuantity -= prodQuantity;
				totalAmount += (prodQuantity * cProduct.pPrice);

				if (cartList.containsKey(prodId)) {
					Product cartProduct = cartList.get(prodId);
					cartProduct.pQuantity += prodQuantity;
				} else {
					Product cart = new Product();
					cart.pBrand = cProduct.pBrand;
					cart.pName = cProduct.pName;
					cart.pPrice = cProduct.pPrice;
					cart.prodId = cProduct.prodId;
					cart.pQuantity = prodQuantity;
					cartList.put(cart.prodId, cart);
				}
				System.out.println("Product is added to cart");
			}
		} else {
			System.out.println("Ooops !! wrong product id.");
		}

	}

	public static void removeFromCart(int prodId, int prodQuantity) {
		if (cartList.containsKey(prodId)) {

			Product cProduct = prodList.get(prodId);
			Product cartProduct = cartList.get(prodId);
			if (cartProduct.pQuantity < prodQuantity) {
				System.out.println("Sorry you don't have enough quantity available !!!");
				System.out.println("Only " + cartProduct.pQuantity + " " + cartProduct.pName + " are available.");
			} else {
				cartProduct.pQuantity -= prodQuantity;
				cProduct.pQuantity += prodQuantity;
				totalAmount -= (prodQuantity * cartProduct.pPrice);
				System.out.println("Product is removed from cart");

			}
		} else {
			System.out.println("Ooops !! wrong product id.");
		}

	}

	public static void listRemainingInventory() {
		System.out.println("***** Here is list of products in inventory *****");
		for (int i : prodList.keySet()) {
			Product product = prodList.get(i);
			System.out.println("Product id: " + product.prodId + "\nProduct name: " + product.pName +
					"\nProduct brand: " + product.pBrand + "\nProduct Quantity: " + product.pQuantity +
					"\nProduct Price: " + product.pPrice);
			System.out.println();
		}
		System.out.println();

	}

	public static void listOfItemInCart() {
		System.out.println("***** Here is list of products in cart *****");
		for (int i : cartList.keySet()) {
			Product product = cartList.get(i);
			System.out.println("Product id: " + product.prodId + "\nProduct name: " + product.pName +
					"\nProduct brand: " + product.pBrand + "\nProduct Quantity: " + product.pQuantity +
					"\nProduct Price: " + product.pPrice);
			System.out.println();
		}
		System.out.println();

	}
}
