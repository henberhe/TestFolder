/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication29;

/**
 *
 * @author henok
 */
public class LispExprEvaluatorException extends RuntimeException {
     public LispExprEvaluatorException()
    {
	this("");
    }

    public LispExprEvaluatorException(String errorMsg) 
    {
	super(errorMsg);
    }
    
}
