package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import entity.cart.Cart;
import entity.cart.CartMedia;
import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import views.screen.popup.PopupScreen;

/**
 * This class controls the flow of place rush order usecase in our AIMS project
 * @author VuongNT
 */
public class PlaceRushOrderController extends BaseController {
	public PlaceRushOrderController() {

    }

    /**
     * check media support rush order 
     * @param id id of media
     * @return
     */
    public boolean checkMediaSupport(int id) {
        //gia su co mot so media ho tro giao nhanh
        if (id == 10|| id == 11 || id == 12 || id == 13 || id == 14) return true;
        return false;
    }

    /**
     * method validate place rush order info 
     * @param info
     * @return value validate
     */
    public boolean validateRushOrderInfo(String info) {
        if (info == null || info.length() == 0) return false;
        for (char c: info.toCharArray()) {
            if (c ==' ' || c==',' || Character.isLetter(c) || c=='.') {
                continue;
            }
            else return false;
        }
        return true;
    }

    /**
     * method validate place rush order instruction 
     * @param instruction
     * @return value validate instruction
     */
    public boolean validateInstruction(String instruction) {
        if (instruction == null || instruction.length() == 0) return false;
        for (char c: instruction.toCharArray()) {
            if (c ==' ' || c==',' || Character.isLetter(c) || c=='.') {
                continue;
            }
            else return false;
        }
        return true;
    }

    /**
     * method validate time receive 
     * @param receiveTime
     * @return
     */
    public boolean validateReceiveTime(String receiveTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime time = LocalDateTime.parse(receiveTime, formatter);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    /**
     * check location is supported place rush order 
     * @param location
     * @return boolean validate
     */
    public boolean checkLocationSupport(String location) {
        //check null or empty
        if (location == null || location.length() == 0 ) return false;

        // get provinces array support rush order
        String[] provinces_supported = Configs.PROVINCES_SUPPORT_RUSH_ORDER;

        //check support location
        for (int i=0 ;i <= provinces_supported.length - 1; i++) {
            if (provinces_supported[i] == location) return true;
        }
        return false;
    }
}
