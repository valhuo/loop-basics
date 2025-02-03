package loopbasics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoopBasics {
    public static void main(String[] args) {
        // Here is a list of tasty foods available in the Twin Cities:

        List<String> twinCitiesFoods = List.of(
            "chana saag", "bánh mì", "お握り",
            "星洲炒米", "Neapolitan pizza", "비빔밥",
            "yassa chicken", "arepas", "លតឆា",
            "tamales", "མོག་མོག", "posole",
            "እንጀራ/ጣይታ/budeena", "พะแนง", "kubay brinj"
        );

        // (This is a not-at-all-subtle hint that you live in a vibrant, cosmopolitan city, and you
        // should get yourself off campus and visit some cool restaurants.)

        // In the List Basics reading, you already saw Java’s for-each loop, which runs some code
        // once for each element of a collection:

        for (String food : twinCitiesFoods) {
            System.out.println("Go try some " + food + "!");
        }

        // The way we read that out loud in English is “for each food in twinCitiesFoods, print....”
        //
        // Let’s break down that syntax just a little more than the List Basics reading did. The
        // pattern is:
        //
        //   for (______ : ______) {
        //       ______
        //   }
        // 
        // Let's give those blank slots some names:
        //
        //   for (❰loop variable❱ : ❰collection❱) {
        //       ❰body❱
        //   }
        //
        // What can go in each of those slots?
        //
        // - loop variable: a variable declaration, like `String food`
        // - collection: any expression that evaluates to a List (or other kinds of collections,
        //     which we'll see later in the course)
        // - body: any sequence of statements

        // In this reading, you will learn the syntax for two other kinds of loops in Java. We’ll
        // break down the syntax in the same way. You will also learn about a couple of ways to
        // control the flow of loops, and learn some patterns of problems to which these different
        // tools are suited.


        System.out.println("────── While Loops ──────");

        // The for-each loop is a good choice when the iterations of the loop correspond to elements
        // of some collection. However, sometimes there is not a collection to iterate over.
        // Sometimes there isn’t even any way to know in advance how many times the loop needs to
        // iterate before it’s done!
        //
        // For these kinds of problem, the right tool is often a _while loop_: we repeat the body of
        // the loop as long as some condition is true. This is just like the while loop in Python,
        // but with a different syntax.

        // For example, suppose we want to make a guess game where the player has to guess several
        // secret words. Here is a way to do that that in Java, using the input syntax you saw in
        // the strings activity:

        Scanner input = new Scanner(System.in);
        // We make secretNums mutable so we can remove correct guesses as we go: 
        List<String> secretWords = new ArrayList<>(List.of("fish", "tree", "turnip"));
        while(!secretWords.isEmpty()) {  // What is the ! operator here? (Answer: “not”)
            System.out.println("There are " + secretWords.size() + " words remaining.");
            System.out.println("Guess a word:");
            String guess = input.nextLine();
            if (secretWords.remove(guess)) {
                System.out.println("You got one!");
            } else {
                System.out.println("Nope, sorry.");
            }
            System.out.println();  // blank line for readability
        }
        System.out.println("Congratulations!");

        // Where is the loop in the code above? What is the loop condition?
        
        // Here is the structure of Java's loop syntax:
        //
        //   while (❰condition❱) {
        //       ❰body❱
        //   }
        //
        // - condition: any expression that evaluates to a _boolean_
        // - body: any sequence of statements
        // 
        // Note that Java’s static type checking means that it will make sure that your loop
        // condition is a boolean _before_ it runs the code! In Java, you can't say, for example,
        // `while(1 + 1)` or `while("toes")`, because those are not boolean expressions.

        // Suppose that we want to do the guessing game with just _one_ secret word. Strangely,
        // this becomes much more awkward! Here’s an approach that almost makes sense...but doesn’t
        // work. Uncomment it to see the error message:

        
        // String secretWord = "bird";
        // while(!guess.equals(secretWord)) {         // We use guess here...
        //     System.out.println("Guess a word:");
        //     String guess = input.nextLine();       // ...but don’t declare it until here
        //     if (guess.equals(secretWord)) {
        //         System.out.println("You got it!");
        //     } else {
        //         System.out.println("Nope, sorry.");
        //     }
        // }
        

        // We could fix this by declaring `guess` outside the loop, and giving it a fake value that
        // is always wrong for the first iteration. But what we really _want_ is to move that loop
        // condition down later in the code, so that we check whether the while loop should go on
        // in the _middle_ of the loop instead of at the top.
        //
        // Java has an way to do that.


        System.out.println("────── Break and Continue ──────");

        // The `break` statement tell Java to immediately exit the loop we’re inside. We can use
        // that to fix up that code above. How? Here’s the idea:
        //
        // 1. At the top of the while loop, we _always_ go on. The condition is always true.
        // 2. In the middle of the loop, after we have the player’s guess, we check if it matches.
        // 3. If it matches, break!
        //
        // A question: how do we do 1? We could say something that is always true, like
        // `while(1 < 2)`, to say “always continue to the next iteration.” That would work, but it
        // is unnecessarily complicated. Remember from above: while loop’s condition is a boolean
        // expression. And what is the simplest boolean expression that is always the value `true`?
        // Well, it’s just `true`! We can say `while(true)` to mean “loop forever.” (The name for
        // that is an “infinite loop.”)
        //
        // But it’s not really an infinite loop, because there is a break statement in the middle!
        // We start out saying it could be infinite, but then stop mid-iteration when it's time to
        // stop. Does this approach make sense? Here is the code:

        String secretWord = "bird";
        while(true) {
            System.out.println("Guess a word:");
            String guess = input.nextLine();
            if (guess.equals(secretWord)) {
                System.out.println("You got it!");
                break;
            } else {
                System.out.println("Nope, sorry.");
            }
        }

        // You will occasionally hear this particular pattern called “repeat until sentinel.” It is
        // good idiom to know, whatever you call it:

        //   while (true) {
        //       do some things
        //       if (time to stop) {
        //           break
        //       }
        //       do some other things
        //   }

        // We can use `break` with for loops too:

        for (String food : twinCitiesFoods) {
            System.out.println("Would you like some " + food + "?");
            String answer = input.nextLine();
            if (answer.toLowerCase().startsWith("y")) {
                System.out.println("OK, here's some " + food + "!");
                break;
            }
        }

        // There is also a Java `continue` statement that means “immediately skip to the next
        // iteration of the loop.” It is less commonly useful than `break`, but is still useful:

        List<String> outOfStock = List.of("星洲炒米", "yassa chicken", "tamales");
        for (String food : twinCitiesFoods) {
            if (outOfStock.contains(food)) {
                continue;  // Don’t offer it if we don’t have it!
            }
            System.out.println("Would you like some " + food + "?");
            String answer = input.nextLine();
            if (answer.toLowerCase().startsWith("y")) {
                System.out.println("OK, here's some " + food + "!");
                break;
            }
        }

        // Note that we could also have implemented the code above in other ways:
        //
        // - We could place the whole body of the loop inside a giant if statement: only do it all
        //   if the food is _not_ out of stock.
        // - We could construct an `availableFoods` list, with `outOfStock` removed, then loop
        //   over that.
        //
        // Either of these approaches might be better...or worse. It depends on the situation, and
        // the tastes of the programmers. There are _always_ many approaches! The important thing is
        // not to learn the single best one and always use it, but rather to see the many
        // alternatives and possibilities, and get comfortable with them.


        System.out.println("────── C-Style for Loops ──────");

        // Here is a simple question: what if we to count from 0 to 99 in Java?
        //
        // In Python, it’s easy enough:
        //
        //   for n in range(100):
        //       print(n)
        //
        // Using the tools we have so far, we can do it in Java, too! But it’s not quite so concise:

        int counter = 0;
        while (counter < 100) {  // Note: we could also say `<= 99` here
            System.out.println(counter);
            counter++;
        }

        // This particular pattern is incredibly common:
        //
        //   ❰initial step❱
        //   while (❰condition❱) {
        //      ...do things...
        //      ❰next step❱
        //   }
        //
        // It is so common, in fact, that Java provides an idiom for it, a second kind of for loop:
        //
        //   for (❰initial step❱; ❰condition❱; ❰next step❱) {
        //      ...do things...
        //   }
        //
        // This is _exactly_ the same as the while loop above (with the one small difference that
        // a variable declared in the ❰initial step❱ is now only visible _inside_ the loop). In this
        // class, we refer to this syntax as the “C-style for loop,” because it originates in the
        // programming language C (which predates Java by ~23 years).
        //
        // Note that the C-style for loop does not add new power to the language. Instead, it takes
        // something that already exists and _rearranges it_, provides a new syntax for it that
        // makes a common idiom more readable and more convenient. When a language provides new,
        // nicer syntax for a feature that already exists, we call this “syntactic sugar” (because
        // it makes the language sweeter).
        
        // Here is that counting while loop again:

        // int n = 0;
        // while (n < 100) {
        //     System.out.println(n);
        //     n++;
        // }

        // And here it is as a C-style for loop:

        for (int n = 0; n < 100; n++) {
            System.out.println(n);
        }

        // Let your brain get used to seeing that syntax. It is a programming idiom that is common
        // across the many, many languages influenced by C. It is such a common idiom, in fact, that
        // VS Code provides a shortcut for typing it. However, we _strongly_ recommend that you
        // spend a few weeks or months typing it out by hand, practicing the syntax until it is
        // clear in your head before you start leaning too hard on the tool to type it for you.
        //
        // In this course, we’ll sometimes refer to this pattern as the “do n times loop.”

        // That syntax is a lot more verbose than Python’s! It is also more flexible, however.
        // Suppose, for example, that we want to count from 1 to 100 instead of 0 to 99:

        for (int n = 1; n <= 100; n++) {
            System.out.println(n);
        }
        System.out.println("Done!");

        // THINK: What _two_ differences are present here from the previous loop? Why?

        // What if we want to count _down_ from 100 to 1? No problem!

        for (int n = 100; n > 0; n--) {
            System.out.println(n);
        }
        System.out.println("Done!");

        // THINK: What changed this time? Why?
        //
        // (Do you see now why Java has the special ++ and -- operators?)
        //
        // PUZZLE: What are _two_ different ways you could change the loop above to count from
        // 100 down to 0 instead of 1?

        // What if instead of counting, we want to...oh, say, list powers of two? No problem!

        for (int n = 1; n < 100000; n *= 2) {
            System.out.println(n);
        }
        System.out.println("Done!");

        // We’ve now wandered beyond just doing something n times.
        //
        // The C-style for loop is very flexible. It can be _too_ flexible sometimes: it is possible
        // to get very clever with it, and “clever” is not always a good thing in code! Usually,
        // the quality we want from code is that it is _obvious_ to the reader. Sometimes there is
        // a clever way of making it more obvious. In that case, clever might be good. But be
        // careful. It is easy to go overboard with a new tool.
        //
        // (If you want to see an example of going overboard with the C-style for loop, look at
        // Collatz.java in this project. And if you want to protect your innocent eyes, don’t look.)

        // Note that, because lists have a get(n) method that takes an integer, it is possible to
        // use the C-style for loop instead of the for-each loop! All we have to do is count through
        // the numerical indices of the list, getting each element in turn. Easy peasy...right?

        for (int n = 0; n <= twinCitiesFoods.size(); n++) {  // ❌ Wrong
            System.out.println("Eat more " + twinCitiesFoods.get(n));
        }

        // Can you spot the error in the code above? Run it and inspect the error message.
        // 
        // Scroll down for the answer.
        //
        // ...
        //
        // ...
        //
        // ...
        // 
        // The elements in a list are numbered counting from 0. If a list has 3 elements, then their
        // indices are 0, 1, 2. Index 3 is out of bounds. However, that list has a size of 3. That
        // means that when we are counting in the loop above, we need to stop _before_ we reach
        // `twinCitiesFoods.size()`. The mistake was saying <= instead of <. Here is the correct
        // code:

        for (int n = 0; n < twinCitiesFoods.size(); n++) {  // ✅ Correct
            System.out.println("Food number " + n + " is " + twinCitiesFoods.get(n));
        }

        // This approach for looping over a list isn’t necessarily a good idea. It is better to use
        // the for-each loop in _most_ situations: it’s easier to read, and the `get(index)` method
        // can be very slow or not available at all for some collections! However, sometimes you
        // need list elements numbered. If so, the approach above _might_ be useful! Note that there
        // is a way to do it with a for-each loop too:

        int foodIndex = 0;
        for (String food : twinCitiesFoods) {
            System.out.println("Food number " + foodIndex + " is " + food);
            foodIndex++;
        }

        // One last thought:
        //
        // The mistake above in the line marked `❌ wrong` is a very common kind of mistake in
        // programming. It’s so common we have a name for it: it is an “off by one error.” Saying <
        // instead of <= when comparing integers, or starting from 1 instead of 0, or looping one
        // too many times, or one too few...all of these mistakes are off by one errors.
        // 
        // Now you can understand an old programmer joke:
        //
        //   “The two hardest problems in computer science is the off by one error.”
        //
        // There are many, many variations of this joke -- and many variations on the looping
        // patterns above. Happy exploring!
    }
}
