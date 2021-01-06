package com.example.restservice;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.restservice.controller.MSMController;
import com.example.restservice.dto.TradeRequest;
import com.example.restservice.service.MSMService;
import com.example.restservice.service.SSIService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MSMController.class)
public class MSMControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MSMService msmService;
	
	@MockBean
	private SSIService ssiService;
	
	String tradeId = "16846548";
	Double amount = 12894.65;
	String currency = "USD";
	String valueDate = "20022020";
	
	@Test
	@Order(1)
	public void createMSM() throws Exception {
		// SSI ssi = ssiService.findBySsiCode(getRandomSsiCode(getSsiCodeList()));
		mockMvc.perform( MockMvcRequestBuilders
			      .post("/api/v1/msm")
			      .content(asJsonString(new TradeRequest(tradeId, "OCBC_DBS_1", amount, currency, valueDate)))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isCreated())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.messageId").exists())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.tradeId").value(tradeId))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(amount))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value(currency))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.valueDate").value(valueDate));
	}
	
	@Test
	@Order(2)
	public void createMSMBadRequest() throws Exception {
		// Invalid SSI Code
		mockMvc.perform( MockMvcRequestBuilders
			      .post("/api/v1/msm")
			      .content(asJsonString(new TradeRequest(tradeId, "OCBC_DBS_3", amount, currency, valueDate)))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest());
	}
	
	@Test
	@Order(3)
	public void retrieveMSM() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .get("/api/v1/msm/{tradeId}", tradeId)
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.tradeId").value(tradeId));
	}
	
	@Test
	@Order(4)
	public void retrieveMSMNotFound() throws Exception {
		// TradeId not found
		mockMvc.perform( MockMvcRequestBuilders
			      .get("/api/v1/msm/{tradeId}", "aabbccddee")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isNotFound());
	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	public String getRandomSsiCode(List<String> list){
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}
	
	public List<String> getSsiCodeList(){
		List<String> ssiCodeList = new ArrayList<>();
		ssiCodeList.add("DBS_OCBC_1");
		ssiCodeList.add("OCBC_DBS_1");
		ssiCodeList.add("OCBC_DBS_2");
		ssiCodeList.add("DBS_SCB");
		ssiCodeList.add("CITI_GS");
		return ssiCodeList;
	}
}
