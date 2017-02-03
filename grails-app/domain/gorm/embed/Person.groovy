package gorm.embed

import groovy.transform.ToString

@ToString
class Person {

    Address homeAddress
    Address workAddress
    static embedded = ['homeAddress', 'workAddress']
    
}
