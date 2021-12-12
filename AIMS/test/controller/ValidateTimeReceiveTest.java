package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateReceiveTime{

	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
	@CsvSource({
			"01-03-2021 14:00,true",
			"01-01-2021 14:22,true",
			"32-08-2020 14:00,false",
			"01-01-2021 25:01,false",
			"abc,false"
	})

	void test(String time, boolean expected) {
		boolean isValid = placeRushOrderController.validateReceiveTime(time);
		assertEquals(isValid, expected);
	}

}