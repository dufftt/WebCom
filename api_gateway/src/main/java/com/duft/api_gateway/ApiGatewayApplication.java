package com.duft.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.duft.api_gateway.Adapter.CustomerService.CustomerServiceAdapter;
import com.duft.api_gateway.Adapter.In.Controller.ApiGatewayRestController;
import com.duft.api_gateway.Adapter.In.Controller.GraphController;
import com.duft.api_gateway.Ports.CustomerPort;


@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}


	@Bean
	public CustomerPort customerPort(){
		return new CustomerServiceAdapter();
	}

	@Bean
	public GraphController graphController(CustomerPort customerPort){
		return new GraphController(customerPort);
	}
	
	@Bean
	public ApiGatewayRestController apiGatewayRestController(CustomerPort customerPort){
    return new ApiGatewayRestController(customerPort);
    }

}

//gemini chat discuss: https://gemini.google.com/share/d351c002d6c9