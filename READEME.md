## Excel-Parser ##

Parcel xlsx file to a simpler pojo

```java
Parser parser = new Parser();
SimpleWorkbook workbook = parser.parse(file);
```

Mark first row as header

```java
parser.firstRowIsHeader();
```

