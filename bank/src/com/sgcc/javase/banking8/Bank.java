package com.sgcc.javase.banking8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bank {

    private List<Customer> customers;

    private Bank(){
        customers = new ArrayList<>();
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

        customers.add(customer);
    }

    /**
     * 返回 表示 customers 变量中有多少个真正的 Customer 对象的整数
     * @return
     */
    public int getNumOfCustomers(){
        return customers.size();
    }

    /**
     * 返回指定索引对应的 Customer 对象
     * @param index
     * @return
     */
    public Customer getCustomer(int index){
        return customers.get(index);
    }

    public Iterator<Customer> getCustomers(){
        return customers.iterator();
    }

}
