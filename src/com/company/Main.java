package com.company;

public class Main {
    public static void main(String[] args) {
        HelloWorld<HelloWorld.HelloImpl> message = new HelloWorld<>();

        System.out.println(message.getMessage());
    }

    static class HelloWorld<String extends HelloWorld.Hello> {
        String thing = (String) HelloFactory.getHello();

        public java.lang.String getMessage() {
            return thing.sayHello("world");
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
}