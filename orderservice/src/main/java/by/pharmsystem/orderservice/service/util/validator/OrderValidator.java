package by.pharmsystem.orderservice.service.util.validator;

import by.pharmsystem.orderservice.entity.Order;
import by.pharmsystem.orderservice.service.exception.BadRequestException;
import by.pharmsystem.orderservice.service.util.ConstantStorage;
import by.pharmsystem.orderservice.service.util.validator.regexp.RegExp;

public final class OrderValidator {
    public static void validate(Order order) {
        if (order.getCost() <= 0) {
            throw new BadRequestException();
        }
        if (order.getAddress() == null || order.getAddress().isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExp.test(RegExp.ADDRESS_REG_EXP, order.getAddress())) {
            throw new BadRequestException();
        }
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            throw new BadRequestException();
        }
        if (!order.getStatus().equals(ConstantStorage.STATUS_NEW) ||
                !order.getStatus().equals(ConstantStorage.STATUS_SENT)) {
            throw new BadRequestException();
        }
    }
}
