package com.lawencon.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.TransactionsDetailOutDao;
import com.lawencon.lms.dto.transactionsout.GetAllTransactionsDetailsOutResDto;
import com.lawencon.lms.dto.transactionsout.GetTransactionsDetailsOutDataDto;
import com.lawencon.lms.dto.transactionsoutexpired.TransactionsOutExpiredResDto;
import com.lawencon.lms.email.EmailHelper;
import com.lawencon.lms.email.ReminderSender;
import com.lawencon.lms.model.TransactionsDetailOut;
import com.lawencon.lms.model.TransactionsOut;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.TransactionsDetailOutService;

@Service
public class TransactionsDetailOutServiceImpl extends BaseServiceLmsImpl implements TransactionsDetailOutService {

	@Autowired
	private TransactionsDetailOutDao transactionsDetailOutDao;

	@Autowired
	private ReminderSender reminderSender;

	@Override
	public GetAllTransactionsDetailsOutResDto findByTransactionOutCode(String code) throws Exception {
		GetAllTransactionsDetailsOutResDto resDto = new GetAllTransactionsDetailsOutResDto();
		List<GetTransactionsDetailsOutDataDto> listOutDataDto = new ArrayList<GetTransactionsDetailsOutDataDto>();
		List<TransactionsDetailOut> listOut = transactionsDetailOutDao.findByTransactionOutCode(code);

		listOut.forEach(detail -> {
			GetTransactionsDetailsOutDataDto data = new GetTransactionsDetailsOutDataDto();
			data.setTransactionsOutCode(detail.getTransactionsOut().getTransactionsOutCode());
			if(detail.getLocations()!=null) {
				data.setLocationsId(detail.getLocations().getId());
			}
			if(detail.getEmployees()!=null) {
				data.setEmployeesId(detail.getEmployees().getId());
				data.setEmployeesCode(detail.getEmployees().getEmployeesCode());
			}
			data.setAssetsId(detail.getAssets().getId());
			data.setAssetsCode(detail.getAssets().getAssetsName());
			data.setExpiredDate(detail.getTransactionDetailOutExpired().toString());
			data.setVersion(detail.getVersion());
			data.setCreatedBy(detail.getCreatedBy());
			data.setCreatedAt(detail.getCreatedAt());
			data.setUpdatedBy(detail.getUpdatedBy());
			data.setUpdatedAt(detail.getUpdatedAt());

			listOutDataDto.add(data);
		});

		resDto.setGetTransactionsDetailsOutDataDto(listOutDataDto);
		resDto.setMessage("SUCCESS");
		return resDto;
	}

	@Override
	public List<TransactionsOutExpiredResDto> getMoreThanExpired() throws Exception {
		List<TransactionsDetailOut> transactionsDetail = transactionsDetailOutDao.findMoreThanExpiredDate();
		List<TransactionsOutExpiredResDto> transactionsExpired = new ArrayList<TransactionsOutExpiredResDto>();
		transactionsDetail.forEach(data -> {
			TransactionsOutExpiredResDto toe = new TransactionsOutExpiredResDto();
			toe.setAssetsName(data.getAssets().getAssetsName());
			toe.setEmployeesFullname(data.getEmployees().getEmployeesFullname());
			toe.setLocationsDeploy(data.getLocations().getLocationsDeploy());
			toe.setTransactionsOutCode(data.getTransactionsOut().getTransactionsOutCode());
			toe.setTransactionsDetailOutExpired(data.getTransactionDetailOutExpired());
			transactionsExpired.add(toe);
		});
		return transactionsExpired;
	}

	@Override
	public List<TransactionsOutExpiredResDto> findAlmostExpired() throws Exception {
		List<TransactionsDetailOut> detail = transactionsDetailOutDao.findAlmostExpired();
		List<TransactionsOutExpiredResDto> transactionsExpired = new ArrayList<TransactionsOutExpiredResDto>();
		detail.forEach(data -> {
			TransactionsOutExpiredResDto toe = new TransactionsOutExpiredResDto();
			toe.setAssetsName(data.getAssets().getAssetsName());
			toe.setEmployeesFullname(data.getEmployees().getEmployeesFullname());
			toe.setLocationsDeploy(data.getLocations().getLocationsDeploy());
			toe.setTransactionsOutCode(data.getTransactionsOut().getTransactionsOutCode());
			toe.setTransactionsDetailOutExpired(data.getTransactionDetailOutExpired());
			transactionsExpired.add(toe);
		});
		return transactionsExpired;
	}

	@Override
	@Async
	public void sendReminder() throws Exception {
		List<TransactionsDetailOut> detail = transactionsDetailOutDao.findAlmostExpired();
		detail.forEach(data -> {
			Users users = new Users();
			TransactionsOut transactionsOut = new TransactionsOut();
			TransactionsDetailOut transactionsDetailOut = new TransactionsDetailOut();

			users.setUsersEmail(data.getEmployees().getUsers().getUsersEmail());
			transactionsOut.setTransactionsOutCode(data.getTransactionsOut().getTransactionsOutCode());
			transactionsDetailOut.setTransactionDetailOutExpired(data.getTransactionDetailOutExpired());

			if (users.getUsersEmail() != null) {
				EmailHelper emailHelper = new EmailHelper();
				emailHelper.setReceiver(users.getUsersEmail());
				emailHelper.setSubject("Almost Expired");
				emailHelper.setBody("Code: " + transactionsOut.getTransactionsOutCode() + " Date: "
						+ transactionsDetailOut.getTransactionDetailOutExpired());
				try {
					reminderSender.sendReminder(data, emailHelper);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		});

	}
}
