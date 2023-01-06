package vttp2022.paf.assessment.eshop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = "/api/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRESTController {

  @Autowired
  private CustomerRepository custRepo;

  @GetMapping(path = "{name}")
  public ResponseEntity<String> getCustomer(@PathVariable String name) {

    try {
      List<Customer> custs = custRepo.findCustomerByName(name);

      // Build the result
      JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
      for (Customer c : custs)
        arrBuilder.add(c.toJSON());
      JsonArray result = arrBuilder.build();

      if (result.isEmpty()) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"error\": \"Customer " + name + " not found\"}");
      }

      return ResponseEntity
          .status(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body(result.toString());

    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .contentType(MediaType.APPLICATION_JSON)
          .body("{\"error\": \"Customer " + name + " not found\"}");
    }

  }

}
