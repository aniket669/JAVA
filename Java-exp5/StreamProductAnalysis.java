import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Comparator;

class Product {
    private String name;
    private double price;
    private String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return String.format("%s (%.2f) in %s", name, price, category);
    }
}

public class StreamProductAnalysis {
    public static void main(String[] args) {
        List<Product> products = List.of(
            new Product("Laptop", 1000, "Electronics"),
            new Product("Headphones", 150, "Electronics"),
            new Product("Bananas", 1.2, "Grocery"),
            new Product("Apples", 2.0, "Grocery"),
            new Product("Office Chair", 250, "Furniture"),
            new Product("Desk", 400, "Furniture")
        );

        // Group products by category
        Map<String, List<Product>> grouped =
                products.stream().collect(Collectors.groupingBy(Product::getCategory));
        System.out.println("Grouped by Category:");
        grouped.forEach((category, list) -> {
            System.out.println(category + " -> " + list);
        });

        // Find the most expensive product in each category
        Map<String, Optional<Product>> maxByCategory =
                products.stream().collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))
                ));
        System.out.println("\nMost Expensive Product per Category:");
        maxByCategory.forEach((category, product) -> 
            System.out.println(category + ": " + product.orElse(null))
        );

        // Calculate average price across all products
        double averagePrice = products.stream()
                .collect(Collectors.averagingDouble(Product::getPrice));
        System.out.printf("\nAverage Price of All Products: %.2f\n", averagePrice);
    }
}
