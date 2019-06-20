//
//  ViewController.swift
//  ios-App
//
//  Created by Jeremy Rempel on 5/19/19.
//  Copyright © 2019 Jeremy Rempel. All rights reserved.
//

import UIKit
import lib

class ViewController: UITableViewController, ContentsView {

    var data: [ContentsResponseRow] = Array()
    
    func onUpdate(data: [ContentsResponseRow]) {
        self.data = data
        
//        let label = UILabel(frame: CGRect(x: 0, y: 0, width: 300, height: 21))
//        label.center = CGPoint(x: 160, y: 285)
//        label.textAlignment = .center
//        label.font = label.font.withSize(25)
//
//        let strMap = data.map { r in r.name + "," }
//        let result = strMap.reduce("", { x, y in x + y } )
//
//        label.text = result
//
//        view.addSubview(label)
        
        self.tableView.reloadData()
    }
    
    func onUpdate(data_ data: ContentsResponseRow) {
    }

    var isUpdating: Bool = false
    
    func showError(error: KotlinThrowable) {
    }
    
    func onUpdate(data: [ContentsResponse]) {
    }
    
    func navigateTo(screen: NavScreen) {
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "customcell", for: indexPath)
        
        cell.textLabel?.text = data[indexPath.item].name
        return cell
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        // todo dependency injection
        let repoInfo = RepoInfo(user: "jeremyrempel", repo: "gitnotestest")
        let client:Ktor_client_coreHttpClient = DIKt.getHttpClient()
        let apiUrl:String = "https://api.github.com"
        
        let uiContext = UI()
        let contentsView = self
        
        let service: GithubApi = DIKt.getService(client: client, apiUrl: apiUrl)
        let presenter = ContentsPresenter(uiContext: uiContext, api: service, repoInfo: repoInfo)
        presenter.view = self
        
        presenter.onRequestData(path: nil)
    }
}
