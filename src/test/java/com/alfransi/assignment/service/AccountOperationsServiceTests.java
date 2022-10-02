package com.alfransi.assignment.service;

import com.alfransi.assignment.common.AppConstants;
import com.alfransi.assignment.converter.Converter;
import com.alfransi.assignment.dto.AccountInfoDto;
import com.alfransi.assignment.dto.TransferRequestDto;
import com.alfransi.assignment.exception.BusinessException;
import com.alfransi.assignment.exception.ResourceNotFoundException;
import com.alfransi.assignment.repositories.AccountsRepository;
import com.alfransi.assignment.services.impl.AccountOperationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@ComponentScan("com.alfransi.assignment.converter")
public class AccountOperationsServiceTests {

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private Converter converter;

	AccountOperationServiceImpl accountOperationService;

	@Before
	public void beforeTests() {
		accountOperationService = new AccountOperationServiceImpl(accountsRepository,converter);
	}

	@Test
	public void getAccountInfo_ResourceNotFoundException(){
		assertThrows(ResourceNotFoundException.class,()->accountOperationService.getAccountInfo("123789123456789"));
	}

	@Test
	public void getAccountInfo_Success(){
		AccountInfoDto accountInfoDto = accountOperationService.getAccountInfo("123456789123456");
		assertEquals("123456789123456",accountInfoDto.getAccountNumber());
		assertEquals(new BigDecimal("200.00"),accountInfoDto.getAvailableBalance());
		assertEquals(new BigDecimal("200.00"),accountInfoDto.getCurrentBalance());
	}

	@Test
	public void createTransfer_AccountTo_ResourceNotFoundException(){
		TransferRequestDto transferRequestDto = TransferRequestDto.builder()
				.accountFrom("123456789123456").accountTo("012345678912349")
				.amount(new BigDecimal("20")).build();

		assertThrows(ResourceNotFoundException.class,()->accountOperationService.createTransfer(transferRequestDto));

	}

	@Test
	public void createTransfer_InsufficientBalance_BusinessException(){
		TransferRequestDto transferRequestDto = TransferRequestDto.builder()
				.accountFrom("123456789123456").accountTo("012345678912345")
				.amount(new BigDecimal("1200")).build();

		assertThrows(BusinessException.class,()->accountOperationService.createTransfer(transferRequestDto));
	}

	@Test
	public void createTransfer_Success(){
		TransferRequestDto transferRequestDto = TransferRequestDto.builder()
				.accountFrom("123456789123456").accountTo("012345678912345")
				.amount(new BigDecimal("20")).build();

		assertEquals(AppConstants.TRANSACTION_SUCCESS,accountOperationService.createTransfer(transferRequestDto));

		AccountInfoDto debitAccountInfoDto = accountOperationService.getAccountInfo("123456789123456");
		AccountInfoDto creditAccountInfoDto = accountOperationService.getAccountInfo("012345678912345");

		assertEquals("123456789123456",debitAccountInfoDto.getAccountNumber());
		assertEquals(new BigDecimal("180.00"),debitAccountInfoDto.getAvailableBalance());
		assertEquals(new BigDecimal("180.00"),debitAccountInfoDto.getCurrentBalance());

		assertEquals("012345678912345",creditAccountInfoDto.getAccountNumber());
		assertEquals(new BigDecimal("120.00"),creditAccountInfoDto.getAvailableBalance());
		assertEquals(new BigDecimal("120.00"),creditAccountInfoDto.getCurrentBalance());
	}


}
