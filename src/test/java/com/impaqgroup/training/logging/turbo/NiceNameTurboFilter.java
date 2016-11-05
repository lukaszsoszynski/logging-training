package com.impaqgroup.training.logging.turbo;

import static ch.qos.logback.core.spi.FilterReply.ACCEPT;
import static ch.qos.logback.core.spi.FilterReply.NEUTRAL;
import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Marker;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;

public class NiceNameTurboFilter extends TurboFilter {

    private String name;

    public FilterReply decide(Marker m, Logger l, Level le, String format, Object[] params, Throwable t) {
        boolean nice = isNice(format, params == null ? emptySet() : new HashSet<>(asList(params)));
        return nice ? ACCEPT :  NEUTRAL;
    }

    private boolean isNice(String format, Set<Object> params) {
        return format.contains(name) || params.contains(name);
    }

    public void setName(String name) {this.name = name;}
}
