package com.boot.lea.mybot;

public class TestJoin {
    public static void main(String[] args) {
        John john1 = new John();
        John john2 = new John();
        John john3 = new John();

        try {
            john1.start();
            john1.join();
            john2.start();
            john2.join(5);
            john3.start();
            john3.join();
        } catch (InterruptedException IE) {
            IE.printStackTrace();
        }
    }
}

class John extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 2; i++)
        {
            try
            {
                Thread.sleep(500);
                System.out.println("Current Thread: "
                        + Thread.currentThread().getName());
            }catch(Exception ex){
                System.out.println("Exception has" +
                        " been caught" + ex);
            }
            System.out.println(i);
        }
    }
}
