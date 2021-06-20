package assignment1.models;

import assignment1.exceptions.InvalidException;

public abstract class ItemModel {

  // all member variables
  private String name;
  private double price, tax, total;
  private int quantity, id;

  // getter methods
  public String getName() {return name; }

  public double getPrice() {return price; }

  public double getTax() {return tax; }

  public int getQuantity() { return quantity; }

  public int getId() { return id; }

  // setter methods
  public void setId(final int id) { this.id = id; }

  public void setName(final String name) { this.name = name; }

  public void setPrice(final double price) { this.price = price; }

  public void setTax(final double tax) { this.tax = tax; }

  public void setQuantity(final int quantity) { this.quantity = quantity; }

  public abstract double calcTax(final double total) throws InvalidException;

  public double getTotal() { return total; }

  public void setTotal(final double total) { this.total = total; }

  // method to calculate total cost.
  public void calculateCost() throws InvalidException{
    double totalCost = this.getPrice() * this.getQuantity();
    double tax = this.calcTax(totalCost);
    this.setTax(tax);
    this.setTotal(totalCost+tax);
  }
}
