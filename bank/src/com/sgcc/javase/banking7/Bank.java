package com.sgcc.javase.banking7;

public class Bank {

    private Customer[] customers;

    private int numberOfCustomer;

    private Bank(){
        customers = new Customer[5];
    }

    private static Bank instance = new Bank();

    public static Bank getBank(){
        return instance;
    }

    /**
     * 根据传入的参数创建一个新的 Cusotmer 对象, 并把该对象赋给 customers 中指定的元素
     * @param firstName
     * @param lastName
     */
    public void addCustomer(String firstName, String lastName){

        Customer customer = new Customer(firstName, lastName);

        customers[numberOfCustomer] = customer;

        numberOfCustomer++;
    }

    /**
     * 返回 表示 customers 变量中有多少个真正的 Customer 对象的整数
     * @return
     */
    public int getNumOfCustomers(){
        return numberOfCustomer;
    }

    /**
     * 返回指定索引对应的 Customer 对象
     * @param index
     * @return
     */
    public Customer getCustomer(int index){
        return customers[index];
    }
}
