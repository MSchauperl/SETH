# This is an adaptation of SETH
* Forked at: https://github.com/rockt/SETH
* Documentation: See http://rockt.github.com/SETH/



  ## Contributors
  - Philippe Thomas
  - Tim Rocktäschel
  - Yvonne Mayer
  - Johannes Kirschnick
  - Eugene Brevdo

## Installation (Linux)
### Install scala version 2.11.7 and add its location to $PATH

You could either download scala binaries from https://www.scala-lang.org/download/2.11.7.html

```
tar -zxf scala-2.11.7.tgz

export PATH=$PATH:~/blabla/scala-2.11.7/bin
```
Or install scala easily with 
```
sudo apt install scala
```


### Install Java development Kit (JDK v8)

You also need to install JDK (version 8) using 
```
sudo apt install openjdk-8-jdk
``` 
You could check your installation with `java -version`.

## Usage
To start, clone this repository
```
git clone https://github.com/helmersl/SETH.git
cd SETH
```
Extract NER muation using the following CLI, note that you need to specify your input data (with `PubTator` format) into `txt` file
```
java -cp seth.jar seth.ner.wrapper.SETHNERAppMut test_input.txt    
```
The results will be wriiten into `muts_extr_seth.txt` file. 

