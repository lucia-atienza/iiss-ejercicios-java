# Práctica 1: Herencia, composición y polimorfismo

## Ejercicios propuestos

### Ejercicio 1

Dados los siguientes fragmentos de código, responder a las siguientes preguntas:

#### `ElementsSet.java`

```java
public class ElementsSet<E> extends HashSet<E> {
    //Number of attempted elements insertions using the "add" method
    private int numberOfAddedElements = 0;

    public ElementsSet() {}

    @Override
    public boolean add(E element) {
        numberOfAddedElements++; //Counting the element added
        return super.add(element);
    } 

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        numberOfAddedElements += elements.size(); //Counting the elements added
        return super.addAll(elements);
    } 

    public int getNumberOfAddedElements() {
        return numberOfAddedElements;
    }
}
```

#### `Main.java`

```java
    ...
    ElementsSet<String> set = new ElementsSet<String>();
    set.addAll(Arrays.asList("One", "Two", "Three"));
    System.out.println(set.getNumberOfAddedElements());
    ...
```

#### Preguntas propuestas

a) ¿Es el uso de la herencia adecuado para la implementación de la clase `ElementsSet`? ¿Qué salida muestra la función `System.out.println` al invocar el método `getNumberOfAddedElements`, 3 o 6?

No es adecuado. Sería mejor usar una composición añadiendo el método `getNumberOfAddedElements` y no heredar de `HashSet`. Además, el atributo `numberOfAddedElements` no tiene sentido (y las operaciones que se hacen sobre él en el código), ya que podemos acceder a ese valor haciendo uso del método `size` de la clase `HashSet`.


Imprimirá 6.

b) En el caso de que haya algún problema en la implementación anterior, proponga una solución alternativa usando composición/delegación que resuelva el problema.


#### `ElementsSet.java`

```java
public class ElementsSet<E> {

    private HashSet<E> set; 

    public ElementsSet() {
        set = new HashSet<E>();
    }

    @Override
    public boolean add(E element) {
        return set.add(element);
    } 

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        return set.addAll(elements);
    } 

    public int getNumberOfAddedElements() {
        return set.size();
    }
}
```

### Ejercicio 2

Dado los siguientes fragmentos de código responder a las siguientes preguntas:

#### `Animal.java`

```java
public abstract class Animal {
    //Number of legs the animal holds
    protected int numberOfLegs = 0;

    public abstract String speak();
    public abstract boolean eat(String typeOfFeed);
    public abstract int getNumberOfLegs();
}
```

#### `Cat.java`

```java
public class Cat extends Animal {
    @Override
    public String speak() {
        return "Meow";
    }

    @Override
    public boolean eat(String typeOfFeed) {
        if(typeOfFeed.equals("fish")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getNumberOfLegs() {
        return super.numberOfLegs;
    }
}
```

#### `Dog.java`

```java
public class Dog extends Animal {
    @Override
    public String speak() {
        return "Woof";
    }

    @Override
    public boolean eat(String typeOfFeed) {
        if(typeOfFeed.equals("meat")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getNumberOfLegs() {
        return super.numberOfLegs;
    }
}
```

#### `Main.java`

```java
    ...
    Animal cat = new Cat();
    Animal dog = new Dog();
    System.out.println(cat.speak());
    System.out.println(dog.speak());
    ...
```

#### Preguntas propuestas

a) ¿Es correcto el uso de herencia en la implementación de las clases `Cat` y `Dog`? ¿Qué beneficios se obtienen?

Como beneficio se obtiene que las clases que deriven de `Animal` tendrán un atributo que todos los animales poseen, `numberOfLegs`. Por lo tanto es correcto que se encuentre en la superclase. Sin embargo, no es correcto que el método `getNumberOfLegs` esté en las subclases, ya que el comportamiento es el mismo para todas ellas, por lo que debería estar en la superclase de forma **no abstracta**. Haciendo este cambio, ya no tiene sentido que el atributo sea protected, ya que no accedemos a él desde las clases derivadas y pasaría a ser `private`. En definitiva, no es correcto el uso de herencia en este caso.


Además, el atributo antes mencionado debería ser un parámetro del constructor, ya que no todos los animales tienen el mismo número de patas.

Otro beneficio sería que no podemos crear instancias de la clase `Animal`, ya que es abstracta y que no tiene sentido. Por lo tanto, no se puede hacer `Animal animal = new Animal();`.



b) En el caso de que el uso de la herencia no sea correcto, proponga una solución alternativa. ¿Cuáles son los beneficios de la solución propuesta frente a la original?

#### `Animal.java`

```java
public abstract class Animal {
    //Number of legs the animal holds
    private int numberOfLegs;
    public abstract String speak();
    public abstract boolean eat(String typeOfFeed);
    public int getNumberOfLegs(){return numberOfLegs;};

    public Animal(int legs) {
        numberOfLegs = legs;
    }

}
```

#### `Cat.java`

```java
public class Cat extends Animal {
    public Cat() {
        super(4);
    }

    @Override
    public String speak() {
        return "Meow";
    }

    @Override
    public boolean eat(String typeOfFeed) {
        if(typeOfFeed.equals("fish")) {
            return true;
        } else {
            return false;
        }
    }

}
```

#### `Dog.java`

```java
public class Dog extends Animal {
    public Dog() {
        super(4);
    }

    @Override
    public String speak() {
        return "Woof";
    }

    @Override
    public boolean eat(String typeOfFeed) {
        if(typeOfFeed.equals("meat")) {
            return true;
        } else {
            return false;
        }
    }

}
```

Con este cambio solo tenemos una función `getNumberOfLegs` y se encuentra en la superclase, ya que el comportamiento era el mismo para todas las subclases, como ya mencionamos anteriormente. Además, el atributo `numberOfLegs` es un parámetro del constructor, por lo que cada subclase tendrá un número de patas distinto. 
