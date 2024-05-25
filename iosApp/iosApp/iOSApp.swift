import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        CoreApplication().initialize(appDeclaration: {_ in })
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
