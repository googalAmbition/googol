namespace java com.tcoding.demo.thrift


struct Book{
    1: optional i64 id;
    2: optional string name;
}

service DemoService{
    string getName(1: i64 id);
    list<Book> listBook();
}
