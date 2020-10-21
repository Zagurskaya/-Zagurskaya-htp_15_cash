package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.Kassa;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public interface KassaService extends Service<Kassa> {

    boolean updateKassaOutSideOperation(Date date, Long id, Long currencyId, Double sum, Long sprOperationId) throws ServiceException;

    List<Kassa> getBallance(User user, Duties duties) throws ServiceException;

}
