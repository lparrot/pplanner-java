import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'

import { AppRoutingModule } from './app-routing.module'
import { AppComponent } from './app.component'
import { HttpClientModule } from '@angular/common/http'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { ReactiveFormsModule } from '@angular/forms'
import { UiModule } from '../../ui/src/lib/ui.module'
import { UiTestComponent } from './pages/ui-test/ui-test.component'
import { LayoutDefaultComponent } from './layouts/layout-default/layout-default.component'
import { LoginComponent } from './pages/login/login.component'

@NgModule({
	declarations: [
		AppComponent,
		UiTestComponent,
		LayoutDefaultComponent,
		LoginComponent,
	],
	imports: [
		BrowserModule,
		HttpClientModule,
		ReactiveFormsModule,
		AppRoutingModule,
		BrowserAnimationsModule,
		UiModule,
	],
	providers: [],
	bootstrap: [ AppComponent ],
})
export class AppModule {
}
