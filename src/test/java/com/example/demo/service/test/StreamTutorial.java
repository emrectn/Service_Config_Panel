package com.example.demo.service.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class StreamTutorial {

	@Autowired
	private PermTypeService permTypeService;

	@Test
	public void deneme() {

		String a = null;
		
		List<PermType> permTypes = permTypeService.findAll();
				
		PermType permType = new PermType.Builder()
				.withPermName("")
				.withId(Integer.valueOf(1))
				.withUsers(Collections.emptyList())
				.createPerm();
		
		/**
		 * emptyIfNull :: Eğer Liste null gelirse NullPointerExceptiondan kurtuluyoruz.
		 * Objects::nonNull Eğer liste içindeki değer null ise NPE kurtuluyoruz.
		 * StringUtils x.getPermname den gelicek null için NPE kurtuluyoruz.
		 **/
		
		System.out.println("NPE kurtulmak için");
		ListUtils.emptyIfNull(permTypes).stream().filter(Objects::nonNull).filter(x -> StringUtils.startsWith(x.getPermname(), "")).forEach(System.out::println);
		assertTrue(CollectionUtils.isNotEmpty(permTypes));
		
		// P ile başlayanları sıralı şekilde gösterelim
		System.out.println("-----------------------------");
		System.out.println("Liste içerisinde m ile başlayanlar");
		ListUtils.emptyIfNull(permTypes).stream().filter(Objects::nonNull).filter(x -> StringUtils.startsWith(x.getPermname().toLowerCase(), "m")).forEachOrdered(System.out::println);
		
		//Eşit Değerlerin elde edilmesi
		System.out.println("-----------------------------");
		System.out.println("Liste elemanı management'a eşit olan");
		List<PermType> example = permTypes.stream().filter(degerler-> "management".equalsIgnoreCase(degerler.getPermname())).collect(Collectors.toList());
		example.stream().forEach(System.out::println);
		
		//Eger içinde geçiyorsa
		System.out.println("-----------------------------");
		System.out.println("Liste elemanları içerisinde 'man' geçen");
		List<PermType> example1 = permTypes.stream().filter(degerler-> degerler.getPermname().toLowerCase().contains("man".toLowerCase())).collect(Collectors.toList());
		example1.stream().forEach(System.out::println);
		
		//Filter için fonksiyon olutşruma
		System.out.println("-----------------------------");
		System.out.println("Filter için fonksiyon olutşruma");
		Stream.of("d2", "a2", "b1", "b3", "c")
		.filter(s -> {
			System.out.println("filter : " + s);
			return true;
		})
		.forEach(s -> System.out.println("forEach : " + s));
		
		//Listedeki isimleri başka listeye taşıma

		System.out.println("-----------------------------");
		System.out.println("Listedeki isimleri başka listeye taşıma");
		List<String> permNames = permTypes.stream().map(x -> x.getPermname()).collect(Collectors.toList());
		System.out.println(permNames);
		
		// Listedeki id'leri toplama
		System.out.println("-----------------------------");
		System.out.println("Listedeki id'leri toplama");
		System.out.println(permTypes.stream().mapToInt(PermType::getId).sum());
		/*ForEach(PermType p : permTypes) gibi
		 * mapToInt(PermType oldugunu biliyoruz nesne adı vermiyoruz. O sınıfın getId fonksiyonunu çağırıyoruz) Tek bir stream oluşturuyoruz.
		 * */
		
		//FlatMap
		System.out.println("-----------------------------");
		System.out.println("Flat Map");
		List<String> firstNames = Arrays.asList("Emre", "Erdem", "Reyyan", "Melih");
		List<String> lastNames = Arrays.asList("Çetin", "Aydin","arik", "pektaş");
		
		List<String> names = Stream.of(firstNames, lastNames).flatMap(l -> l.stream()).collect(Collectors.toList());
		names.forEach(System.out::println);
		
		/* Source
		 * https://medium.com/@sinanselimoglu/java-8-stream-yap%C4%B1s%C4%B1na-nazik-bir-giri%C5%9F-f2a5977215c6
		 * https://medium.com/@sinanselimoglu/java-8-streams-fonksiyonlar-2-82ab8b309b15
		 * https://medium.com/@sinanselimoglu/java-8-streams-paralel-i%CC%87%C5%9Flemler-3-a01233c8fd9f
		 * 
		 * */
		

		
		
	}
}
