package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateInstructionTest {
	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
	@CsvSource({
			"Nguyen Tien Vuong,true",
			"12abc,false"
	})

	void test(String instruction, boolean expected) {
		boolean isValid = placeRushOrderController.validateInstruction(instruction);
		assertEquals(isValid, expected);
	}

}