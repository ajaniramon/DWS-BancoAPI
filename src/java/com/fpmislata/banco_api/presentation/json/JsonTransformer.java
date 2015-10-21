/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco_api.presentation.json;

/**
 *
 * @author alumno
 */
public interface JsonTransformer {

    public String toJson(Object object);

    public <T> T fromJson(String json, Class<T> clazz);
}
