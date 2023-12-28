package com.myproject.commandes.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-config-ms")
@RefreshScope
public class ApplicationPropertiesConfiguration {
    private int limitDeCommandes;
    public int getLimitDeCommandes() {
        return limitDeCommandes;
    }

    public void setLimitDeCommandes(int limitDeCommandes) {
        this.limitDeCommandes = limitDeCommandes;
    }


}
