package com.ruoyi.ruoyiasset.third;


import com.ruoyi.ruoyiasset.service.IAssetService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
@Log4j2
public class TimeTaskManager {
    @Autowired
    IAssetService assetService;


    @PostConstruct
    public void init() {
        log.info("assetTimer is running");
        Timer assetTimer = new Timer("assetTimer");
        assetTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    log.info("assetTimer is running...");
                    List<String> symbolList = assetService.selectSymbolList();
                    log.info("symbolList size: {}", symbolList.size());
                    for (String symbol : symbolList) {
                        log.info("Processing symbol: {}", symbol);
                        assetService.getSymbol(symbol, true);
                    }
                } catch (Exception e) {
                    log.error("Exception in assetTimer.scheduleAtFixedRate", e);
                }
            }
            // 十分钟刷新一次
        }, 0, 10 * 60 * 1000L);
    }
}
