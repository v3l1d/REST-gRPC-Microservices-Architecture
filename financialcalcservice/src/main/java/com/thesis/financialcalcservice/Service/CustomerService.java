package com.thesis.financialcalcservice.Service;

import com.thesis.financialcalcservice.model.Customer;
import com.thesis.financialcalcservice.repository.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;

    private final Logger logger= LogManager.getLogger(CustomerService.class);

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    public void savePersonIfNotExists(Customer customer){
        if(customerRepository.findByEmail(customer.getEmail())==null){
            customerRepository.save(customer);

        }else{
            logger.error("Person with this email already exists!");
        }
    }

    public boolean findCustomerByEmail(String email){
        return customerRepository.findByEmail(email) != null;
    }

    public Customer getCustomerByEmail(String email){
        return customerRepository.findByEmail(email);
    }

    public void setMailVerified(String email){
        if(findCustomerByEmail(email)){
            Customer temp=customerRepository.findByEmail(email);
            temp.setMailVerified(true);
            customerRepository.save(temp);
        }

    }
    public void setSMSVerified(String email){
        if(findCustomerByEmail(email)){
            Customer temp=customerRepository.findByEmail(email);
            temp.setSMSverified(true);
            customerRepository.save(temp);
        }

    }


}
