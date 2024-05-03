package com.example.project.Dealership;

import com.example.project.Dealership.Entity.Make;
import com.example.project.Dealership.Entity.Vehicle;
import com.example.project.Dealership.ServiceLayer.InventorySL;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class DealershipApplicationTests {

	@Autowired
	InventorySL inventorySL;

	@Test
	void contextLoads() throws JSONException {


		Page<Vehicle> vehiclesPage = inventorySL.getSearchedVehicles(1, 6, "asc", "saleprice", "true", "");
		System.out.println(vehiclesPage);
// Convert Page<Vehicle> to JSON
		//Gson gson = new Gson();
		//System.out.println(gson.toJson(vehiclesPage.getContent()));

// Print JSON array
		//System.out.println(jsonArray.toString());


	}

	@Test
	void NewVehcilesTest(){

		Gson gson = new Gson();
		// convert your list to json
		String jsonList = gson.toJson(inventorySL.getMakes());

		System.out.println(jsonList);
	}

}
