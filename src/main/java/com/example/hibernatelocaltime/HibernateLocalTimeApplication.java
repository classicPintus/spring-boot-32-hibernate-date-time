package com.example.hibernatelocaltime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.*;

@SpringBootApplication
@Slf4j
public class HibernateLocalTimeApplication {

    public static void main(String[] args) {
        var applicationContext = SpringApplication.run(HibernateLocalTimeApplication.class, args);

        var dummyRepository = applicationContext.getBean(DummyRepository.class);

        ZoneId zoneId = ZoneId.of("UTC");
        {
            String useCaseName = "nano";

            var nano = new DummyEntity();
            nano.setId(System.currentTimeMillis());
            nano.setName(useCaseName);

            var expectedMyTime = LocalTime.NOON;
            nano.setLocalTime(expectedMyTime);

            Instant instant = LocalDate.now().atTime(LocalTime.MAX).atZone(zoneId).toInstant();
            nano.setInstant(instant);

            LocalDateTime max = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
            nano.setLocalDateTime(max);

            dummyRepository.save(nano);

            DummyEntity dummyEntity = dummyRepository.findByName(useCaseName).get();
            log.info("{}: expected {} saved {}", useCaseName, expectedMyTime, dummyEntity.getLocalTime());
        }

//        {
//            var expectedMyTime = LocalTime.MAX.withNano(999_999_000);
//            var micro = new DummyEntity();
//            micro.setId(System.currentTimeMillis());
//            String useCaseName = "micro";
//            micro.setName(useCaseName);
//            micro.setLocalTime(expectedMyTime);
//            dummyRepository.save(micro);
//
//            log.info("{}: expected {} saved {}", useCaseName, expectedMyTime, dummyRepository.findByName(useCaseName).get().getLocalTime());
//        }
//
//        {
//            var expectedMyTime = LocalTime.MAX.withNano(999_000_000);
//            var milli = new DummyEntity();
//            milli.setId(System.currentTimeMillis());
//            String useCaseName = "milli";
//            milli.setName(useCaseName);
//            milli.setLocalTime(expectedMyTime);
//            dummyRepository.save(milli);
//
//            log.info("{}: expected {} saved {}", useCaseName, expectedMyTime, dummyRepository.findByName(useCaseName).get().getLocalTime());
//        }
    }

}
