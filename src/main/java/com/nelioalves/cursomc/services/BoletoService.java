package com.nelioalves.cursomc.services;

import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

  public void buildPagamentoBoleto(PagamentoComBoleto pag, Date instante) {
    
    Calendar  cal = Calendar.getInstance();
    cal.setTime(instante);
    cal.add(Calendar.DAY_OF_MONTH, 7);
    
    pag.setDataVencimento(cal.getTime());
  }
  
}
