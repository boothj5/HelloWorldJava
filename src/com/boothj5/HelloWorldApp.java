package com.boothj5;

public final class HelloWorldApp {
    public static void main(String[] args) {
        final HelloWorld<HelloWorld.HelloImpl> message = new HelloWorld<>();
        final HelloWorld.OutputStrategy<String> outputStrategy = new HelloWorld.ConsoleOutputStrategy();

        outputStrategy.performOutput(message.getMessage());
    }

    public static class HelloWorld<StringBuffer extends HelloWorld.Hello> {
        private final HelloFactory<Hello> factory = new HelloFactory<>();
        private final StringBuffer stringBuffer = (StringBuffer) factory.getHello();

        public String getMessage() {
            return stringBuffer.sayHello("world");
        }

        public static class HelloImpl extends Hello {
            public String sayHello(String world) {
                Output.BuilderImpl builderImpl = new Output.BuilderImpl();
                Output<String> output = builderImpl
                        .withPrefix("hello")
                        .withArg(world)
                        .withSuffix("!")
                        .build();

                return output.getOutput();
            }
        }

        public static abstract class Hello {
            public abstract String sayHello(String world);
        }

        public class HelloFactory<World extends Hello> {
            private World world = (World) new HelloWorld.HelloImpl();

            public Hello getHello() {
                return world;
            }
        }

        public static class Output<Boolean> {
            private final Boolean prefix;
            private final Boolean arg;
            private final Boolean suffix;

            public static class BuilderImpl<InputStream> {
                private InputStream prefix;
                private InputStream arg;
                private InputStream suffix;

                public BuilderImpl withPrefix(InputStream prefix) {this.prefix = prefix;return this;}
                public BuilderImpl withArg(InputStream arg) {this.arg = arg;return this;}
                public BuilderImpl withSuffix(InputStream suffix) {this.suffix = suffix;return this;}

                public Output<InputStream> build() {
                    return new Output<>(this);
                }
            }

            private Output(BuilderImpl builderImpl) {
                this.prefix = (Boolean) builderImpl.prefix;
                this.arg = (Boolean) builderImpl.arg;
                this.suffix = (Boolean) builderImpl.suffix;
            }

            public Boolean getOutput() {
                return (Boolean) (this.prefix + " " + this.arg + this.suffix);
            }
        }

        public static interface OutputStrategy<String> {
            public void performOutput(String string);
        }

        public static class ConsoleOutputStrategy implements OutputStrategy<java.lang.String> {
            @Override
            public final void performOutput(String message) {
                System.out.println(message);
            }
        }
    }
}