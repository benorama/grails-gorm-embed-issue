# Grails GORM embedded domain issue

## Config

Grails **3.2.5**

## Description

This app has one domain class with embedded domain components.

```groovy
class Person {

    Address homeAddress
    Address workAddress
    
    static embedded = ['homeAddress', 'workAddress']
    
}
```

```groovy
class Address {

    Integer number
    String code
    
}
```

In Grails **3.2.3** (GORM **6.0.4**) embedded properties are correctly saved during update.
Since Grails **3.2.4** (GORM **6.0.5**) embedded properties changes are not persisted anymore.

To reproduce the issue:

* `grails run-app`
* go to `/console`
* create a person

```groovy
person = new Person()
person.homeAddress = new Address(number: 1, code: 'A')
person.workAddress = new Address(number: 2, code: 'B')
person.save()
```

* update work address

```groovy
person = Person.get(1)
person.workAddress.code = 'C'
person.save()
```

* show work address

```groovy
person = Person.get(1)
person.workAddress
```

It will still show code=**B**

## Workaround

No workaround, rollback to Grails **3.2.3**.