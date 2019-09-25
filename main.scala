import scala.util.Random
case class GameState(var userPoint: Int = 0, var totalTour: Int = 0) {}

object CoinFlip extends App {
  
  val random = Random
  val gameState = GameState(0, 0)
  mainLoop(gameState, random)


  def mainLoop(gameState: GameState, random: Random): Unit = {
    
    //Demand the choice to user
    showChoices()
    val playerChoice = getPlayerChoice()
    
    //If the user want to play
    if(playerChoice == "h" || playerChoice == "t"){
      
      //Flip the coin
      val flipResult = flipTheCoin(random)
      
      //Compare player choice and real value
      if(playerChoice == flipResult){
        var newGameState = gameState.copy(gameState.userPoint + 1, gameState.totalTour + 1)
        displayState(newGameState, flipResult)
        mainLoop(newGameState, random)
      }else{
        var newGameState = gameState.copy(gameState.userPoint, gameState.totalTour + 1)
        displayState(newGameState, flipResult)
        mainLoop(newGameState, random)
      }
      
    }else{
      
      //End of the game
      displayEnd(gameState)
      
    }
    
  }
  
  def showChoices(): Unit = {
    println("")
    print("(h)eads, (t)ails, start a (n)ew game or (q)uit: ")
  }

  def getPlayerChoice(): String = {
    println("")
    scala.io.StdIn.readLine()
  }
  
  def flipTheCoin(random: Random): String = {
    if(random.nextInt(2) == 0) "h"
    else "t"
  }
  
  def displayState(gameState: GameState, flipResult: String): Unit = {
    if(flipResult == "h") println("Flip was Heads. #Flips: ", gameState.totalTour.toString(), " #Correct: ", gameState.userPoint.toString())
    else println("Flip was Tails. #Flips: ", gameState.totalTour.toString(), " #Correct: ", gameState.userPoint.toString())
  }
  
  def displayEnd(gameState: GameState): Unit = {
    println("=== GAME OVER ===")
    println("#Flips: ", gameState.totalTour.toString(), " #Correct: ", gameState.userPoint.toString())
  }
  
}