For problem 1 of the homework, my code is able to simulate the problem provided by asking the user to input N number of guests attending the party. It then creates a thread for each guest attending, but only allows one person to enter the room with the cupcake at a time. These threads are not allowed to communicate directly with each other while the code is running to ensure the design and correctness of the design prompt holds true. In order for the guests to tell their host that everyone has visited without communicating with each other, it was decided beforehand that one thread would be the tracker that keeps track of the known number of visits. The rest will only eat once and then never again, never refilling the plate. Once the tracker thread enters the room he knows that one new guest had a cupcake from the empty plate, making a mental note to the count in his head, he then refills it for the next guest. While this method requires the tracker thread to visit at least 2N number of times, it ensures the correctness of the problem while meeting its criteria.

For problem 2 of the homework, my code would again ask the user to input N number of guests, only this time, these threads would randomly decide whether they want to see the vase or not. If they do then they will enter a queue and be allowed entry once they reach the front of the line and the last guest has left the room. I believe that this method out of the three was the best because unlike the first option, this choice avoided cluttering, and unlike the second option this method would gurantee that a guest could see the vase. If left with a sign that said "Avaliable" or "Busy", a particular thread may experience bad luck and only ever see Busy, never getting the chance to enter the room providing a clear disatvantage. While the third method does have to make a guest wait unlike the other two methods, the third method is more fair to the guests as a whole and should be chosen to give a more defenitive way of knowing when they can see the vase. Since the design did not provide a stop condition I would end the code after 15 seconds had passed, using the console to verify the correctness of my code.
