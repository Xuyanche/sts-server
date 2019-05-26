package bgroup.stocktradingsystem.stsserver.controller;

import bgroup.stocktradingsystem.stsserver.domain.PersonalAccount;
import bgroup.stocktradingsystem.stsserver.domain.Stock;
import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import bgroup.stocktradingsystem.stsserver.service.StockService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class StockController {
    @Autowired
    StockService stockService;

    private Gson gson = new Gson();

    @RequestMapping(value = "/stock/all", method = POST)
    @ResponseBody
    public String fetchAllStock() {
        return new CustomResponse(new Result(true),
                stockService.fetchAllStock()).toString();
    }

    @RequestMapping(value = "/stock/update", method = POST)
    @ResponseBody
    public String updateStock(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        Stock stock = gson.fromJson(data, Stock.class);
        stockService.updateStock(stock);
        return new CustomResponse(new Result(true)).toString();
    }

    @RequestMapping(value = "/stock/update_list", method = POST)
    @ResponseBody
    public String updateStockList(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        Type listType = new TypeToken<ArrayList<Stock>>(){}.getType();
        List<Stock> stocks = new Gson().fromJson(data, listType);
        stockService.updateStockList(stocks);
        return new CustomResponse(new Result(true)).toString();
    }

}