package main

import (
	"fmt"
	"math"
)

func slargelement(arr []int) (int, error) {
	if len(arr) < 2 {
		return 0, fmt.Errorf("Array doesn't have two element")
	}

	largest := math.MinInt
	sLargest := math.MinInt

	for _, value := range arr {
		if value > largest {
			sLargest = largest
			largest = value
		} else if value > sLargest && value != largest {
			sLargest = value
		}
	}

	if sLargest == math.MinInt {
		return 0, fmt.Errorf("no second largest element in array")
	}

	return sLargest, nil
}

func main() {
	arr := []int{10,20,30,40,50,100}
	sLargest, err := slargelement(arr)
	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Printf("The second largest element is: %d\n", sLargest)
	}
}
