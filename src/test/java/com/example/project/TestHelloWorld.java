package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;

public class TestHelloWorld {

   @Test
   public void testHelloWorld()
   {
     PrintStream originalOut = System.out;
     ByteArrayOutputStream bos = new ByteArrayOutputStream();
     System.setOut(new PrintStream(bos));

     // action
     HelloWorld.main(null);

     // assertion
     assertEquals("Hello IT 211! Congrats, you're more than half way through the quarter!\n", bos.toString());

     // undo the binding in System
     System.setOut(originalOut);
   }
}
