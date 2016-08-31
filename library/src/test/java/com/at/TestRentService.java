package com.at;

import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.at.library.dao.RentDao;
import com.at.library.dto.RentDTO;
import com.at.library.model.Rent;
import com.at.library.service.rent.RentService;
import com.at.library.service.rent.RentServiceImpl;
import org.dozer.DozerBeanMapper;

@RunWith(MockitoJUnitRunner.class)
public class TestRentService {
	
	private static final Date INIT = new Date();
	
	@InjectMocks
	final RentService rentservice = new RentServiceImpl();
	
	final Rent r = new Rent();
	
	@Mock
	private DozerBeanMapper dozer;
	
	@Mock
	private RentDao rDao;
	
	
	@Before
	public void ini(){
		final RentDTO rDTO = new RentDTO();
		r.setEndDate(INIT);
		Mockito.when(dozer.map(r, RentDTO.class)).thenReturn(rDTO);
		//Mockito.when(rDao.save(rent)).theRenturn(value);
		
	}
	
	@Test
	public void transform(){
		
		//final RentService rentservice = new RentServiceImpl();
		
		//final RentDTO rDTO = rentservice.transform(r);
		//Assert.assertEquals("Fecha",rDTO.getEndDate(), INIT);
	}
	
	@Test
	@Ignore
	public void create(){
		r.setEndDate(INIT);
		
	}

}
