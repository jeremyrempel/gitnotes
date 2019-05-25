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
        
        let label = UILabel(frame: CGRect(x: 0, y: 0, width: 300, height: 21))
        label.center = CGPoint(x: 160, y: 285)
        label.textAlignment = .center
        label.font = label.font.withSize(25)
        
        
        let strMap = data.map { r in r.name + "," }
        let result = strMap.reduce("", { x, y in x + y } )
        
        label.text = result
        
        view.addSubview(label)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        // todo dependency injection
        let user:String = "jeremyrempel"
        let repo:String = "gitnotestest"
        let client:Ktor_client_coreHttpClient = DIKt.getHttpClient()
        let apiUrl:String = "https://api.github.com"
        
        let uiContext = UI()
        let contentsView = self
        
        let service: GithubApi = DIKt.getService(user: user, repo: repo, client: client, apiUrl: apiUrl)
        let presenter = ContentsPresenter(uiContext: uiContext, view: contentsView, api: service)
        
        presenter.onRequestData()
        
        //        let result = JsonTest().getPerson()
        
    }
}

