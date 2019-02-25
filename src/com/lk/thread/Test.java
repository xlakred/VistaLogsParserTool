package com.lk.thread;

public class Test {

    public static void main(String[] args) {
        String s = "<ApplicationArea><Sender><LogicalId>Volvo</LogicalId><Component>TMS</Component><Task>Hold</Task><AuthorizationId>TMS</AuthorizationId></Sender><CreationDateTime>2018-12-17T02:56:33</CreationDateTime><BODId>caf58ab1-9165-4009-907d-a6cbee15fafc</BODId></ApplicationArea><DataArea><Process></Process><Hold><BrandOccurrence><Brand>YV1</Brand><EventCode>10150</EventCode><FactoryOrderNumber>644711806</FactoryOrderNumber><LocationFrom>JPTHS0001</LocationFrom><BrandEventTimeStamp>2018-12-17T10:56:20</BrandEventTimeStamp><BrandProcessTimeStamp>2018-12-17T01:56:20</BrandProcessTimeStamp></BrandOccurrence><HoldAction>0</HoldAction><Code>QUALH</Code></Hold></DataArea></ProcessHold>";
        System.out.println(s.substring(s.indexOf("<BODId>") + 7, s.indexOf("</BODId>")));
    }
}