/**
  * It is a more powerful version of the switch statement in Java.
  * It can likewise be used in place of a series of if/else statements
  */
import scala.util.Random

abstract class Notification

case class Email(sender: String, title: String, body: String)
    extends Notification

case class SMS(caller: String, message: String) extends Notification

case class VoiceRecording(contactName: String, link: String)
    extends Notification

object Notification {
  def showNotification(notification: Notification): String = {
    notification match {
      case Email(sender, title, _) =>
        s"You got an email from $sender with title: $title"
      case SMS(number, message) =>
        s"You got an SMS from $number! Message: $message"
      case VoiceRecording(name, link) =>
        s"you received a Voice Recording from $name! Click the link to hear it: $link"
    }
  }

  // pattern guards
  def showImportantNotification(
      notification: Notification,
      importantPeopleInfo: Seq[String]
  ): String =
    notification match {
      case Email(sender, title, _) if importantPeopleInfo.contains(sender) =>
        "You got an email from special someone!"
      case SMS(number, _) if importantPeopleInfo.contains(number) =>
        "You got an SMS from special someone!"
      case other =>
        showNotification(other)
    }
}

abstract class Device
case class Phone(model: String) extends Device {
  def screenOff = "Turning screen off"
}
case class Computer(model: String) extends Device {
  def screenSaverOn = "Turning screen saver on..."
}

// companion object: has access to private as well
object Device {
  // syntax for matching on the type only
  def goIdle(device: Device) = device match {
    case p: Phone    => p.screenOff
    case c: Computer => c.screenSaverOn
  }
}

// Traits and classes can be marked sealed which means all subtypes must be declared in the same file
// This assures that all subtypes are known.
sealed abstract class Furniture
case class Couch() extends Furniture
case class Chair() extends Furniture

object Furniture {
  def findPlaceToSit(piece: Furniture): String = piece match {
    case a: Couch => "Lie on the couch"
    case b: Chair => "Sit on the chair"
  }
}

object PatterMatchingDemo {
  def matchTest(x: Int): String = x match {
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    case _ => "other"
  }

  def main(args: Array[String]) {
    println(matchTest(Random.nextInt(10)))

    val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")
    val someSms = SMS("867-5309", "Are you there?")
    val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")
    val importantEmail =
      Email("jenny@gmail.com", "Drinks tonight?", "I'm free after 5!")
    val importantSms = SMS("867-5309", "I'm here! Where are you?")
    println(Notification.showImportantNotification(someSms, importantPeopleInfo))
    println(Notification.showImportantNotification(someVoiceRecording, importantPeopleInfo))
    println(Notification.showImportantNotification(importantEmail, importantPeopleInfo))
    println(Notification.showImportantNotification(importantSms, importantPeopleInfo))
  }
}
