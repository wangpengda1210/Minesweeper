// write the EspressoMachine class here
class EspressoMachine {
    
    val costPerServing: Float
    
    constructor(coffeeCapsulesCount: Int, totalCost: Float) {
        costPerServing = totalCost / coffeeCapsulesCount
    }
    
    constructor(coffeeBeansWeight: Float, totalCost: Float) {
        val numServings = coffeeBeansWeight / 10.6f
        costPerServing = totalCost / numServings
    }
    
}