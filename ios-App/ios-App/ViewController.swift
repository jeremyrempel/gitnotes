//
//  ViewController.swift
//  ios-App
//
//  Created by Jeremy Rempel on 5/19/19.
//  Copyright © 2019 Jeremy Rempel. All rights reserved.
//

import UIKit
import app

class ViewController: UIViewController, ContentsView {
    
    var isUpdating: Bool = false
    
    func showError(error: KotlinThrowable) {
        
    }
    
    func onUpdate(data: [ContentsResponse]) {
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        
        // todo dependency injection
        let user:String = "jeremyrempel"
        let repo:String = "gitnotestest"
        let client:Ktor_client_coreHttpClient = DIKt.getHttpClient()
        let apiUrl:String = "https://api.github.com"
        
        let service: GithubApi = DIKt.getService(user: user, repo: repo, client: client, apiUrl: apiUrl)
 
        
//        let view =
//
//        let actions = DIKt.getActions(coroutineContext: <#T##KotlinCoroutineContext#>, view: <#T##ContentsView#>, service: <#T##GithubApi#>)
//
        
        let result = JsonTest().getPerson()
        
        let label = UILabel(frame: CGRect(x: 0, y: 0, width: 300, height: 21))
        label.center = CGPoint(x: 160, y: 285)
        label.textAlignment = .center
        label.font = label.font.withSize(25)
        label.text = result.lname + ", " + result.fname
        view.addSubview(label)
    }
}

