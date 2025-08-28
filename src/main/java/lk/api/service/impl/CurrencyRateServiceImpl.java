package lk.api.service.impl;

import lk.api.dto.CurrencyRateDto;
import lk.api.model.CurrencyRate;
import lk.api.repository.CurrencyRateRepo;
import lk.api.service.CurrencyRateService;
import lk.api.util.ModelMapperConfig;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final ModelMapperConfig modelMapper;
    private final CurrencyRateRepo currencyRateRepo;

    public CurrencyRateServiceImpl(ModelMapperConfig modelMapper, CurrencyRateRepo currencyRateRepo) {
        this.modelMapper = modelMapper;
        this.currencyRateRepo = currencyRateRepo;
    }

    @Override
    public CurrencyRateDto saveRate(CurrencyRateDto currencyRateDto) {
        CurrencyRate rate = dtoToEntity(currencyRateDto);
        CurrencyRate save = currencyRateRepo.save(rate);
        return entityToDto(save);
    }

    @Override
    public CurrencyRateDto updateRate(Long rateId, CurrencyRateDto currencyRateDto) {
        Optional<CurrencyRate> byId = currencyRateRepo.findById(rateId);
        if (byId.isPresent()) {
            currencyRateDto.setCurrencyId(byId.get().getCurrencyId());
            CurrencyRate rate = dtoToEntity(currencyRateDto);
            CurrencyRate save = currencyRateRepo.save(rate);
            return entityToDto(save);
        } else {
            return null;
        }
    }

    @Override
    public CurrencyRateDto deleteRate(Long rateId) {
        Optional<CurrencyRate> byId = currencyRateRepo.findById(rateId);
        if (byId.isPresent()) {
            currencyRateRepo.deleteById(rateId);
            return entityToDto(byId.get());
        } else {
            return null;
        }
    }

    @Override
    public CurrencyRateDto searchRate(String sentCurrency, String receiveCurrency) {
        CurrencyRate result = currencyRateRepo.findBySentCurrencyAndReceiveCurrency(sentCurrency, receiveCurrency);
        return entityToDto(result);
    }

    @Override
    public Set<String> getAllRate() {
        List<CurrencyRate> all = currencyRateRepo.findAll();
        Set<String> allRate = new HashSet<>();
        for (CurrencyRate value : all) {
            allRate.add(value.getSentCurrency());
            allRate.add(value.getReceiveCurrency());
        }
        return allRate;

    }

    private CurrencyRateDto entityToDto(CurrencyRate save) {
        if (save != null) {
            return modelMapper.modelMapper().map(save, CurrencyRateDto.class);
        } else {
            return null;
        }
    }

    private CurrencyRate dtoToEntity(CurrencyRateDto currencyRateDto) {
        return modelMapper.modelMapper().map(currencyRateDto, CurrencyRate.class);
    }
}
