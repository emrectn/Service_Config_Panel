package com.example.demo.service.test;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.MainApplication;
import com.example.demo.model.PermType;
import com.example.demo.service.PermTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class Tutorial {

	@Autowired
	private PermTypeService permTypeService;

	@Test
	public void deneme() {

		String a = null;
		
		List<PermType> permTypes = permTypeService.findAll();
		
		/**
		 * emptyIfNull :: Eğer Liste null gelirse NullPointerExceptiondan kurtuluyoruz.
		 * Objects::nonNull Eğer liste içindeki değer null ise NPE kurtuluyoruz.
		 * StringUtils x.getPermname den gelicek null için NPE kurtuluyoruz.
		 **/
		
		ListUtils.emptyIfNull(permTypes).stream().filter(Objects::nonNull).filter(x -> StringUtils.startsWith(x.getPermname(), "")).forEach(System.out::println);
		assertTrue(CollectionUtils.isNotEmpty(permTypes));
		
		// P ile başlayanları sıralı şekilde gösterelim
		System.out.println("-----------------------------");
		ListUtils.emptyIfNull(permTypes).stream().filter(Objects::nonNull).filter(x -> StringUtils.startsWith(x.getPermname().toLowerCase(), "m")).forEachOrdered(System.out::println);
		
	}
}
