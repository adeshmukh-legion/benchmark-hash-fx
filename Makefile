clean:
	rm -rf target/

target/benchmark-hash-fx.jar:
	mvn clean verify

build: target/benchmark-hash-fx.jar

run: build
	java -jar target/benchmark-hash-fx.jar -rf json -i 2 -r 2 -wi 1 -w 5
