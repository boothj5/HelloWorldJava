package com.company;

public class Main {
    public static void main(String[] args) {
        HelloWorld<HelloWorld.HelloImpl> message = new HelloWorld<>();

        OutputStrategy<String> outputStrategy = new OutputStrategy<String>() {
            @Override
            public void performOutput(String message) {
                System.out.println(message);

            }
        };

        outputStrategy.performOutput(message.getMessage());
    }

    static class HelloWorld<String extends HelloWorld.Hello> {
        String string = (String) HelloFactory.getHello();

        public java.lang.String getMessage() {
            return string.sayHello("world");
        }

        public static class HelloImpl extends Hello {
            public java.lang.String sayHello(java.lang.String world) {
                return "hello " + world;
            }
        }

        static abstract class Hello {
            public abstract java.lang.String sayHello(java.lang.String world);
        }

        static class HelloFactory<World> {
            private static Hello world = new HelloWorld.HelloImpl();

            public static <World extends Hello> Hello getHello() {
                return world;
            }
        }
    }

    static interface OutputStrategy<String> {
        public void performOutput(String message);
    }
}