export type TailwindColors = 'default' | 'primary' | 'secondary' | 'success' | 'info' | 'warn' | 'danger';

export type TailwindMaxWidthSize = 'none' | 'xs' | 'sm' | 'md' | 'lg' | 'xl' | '2xl' | '3xl' | '4xl' | '5xl' | '6xl' | '7xl' | 'full' | 'min' | 'max' | 'prose' | 'screen-sm' | 'screen-md' | 'screen-lg' | 'screen-xl' | 'screen-2xl'

export type AlertPosition = 'bottom' | 'top' | 'bottomLeft' | 'bottomRight' | 'topLeft' | 'topRight';

export class Alert {
	constructor(
		public id: number,
		public type: AlertType,
		public title: string,
		public message: string,
		public timeout: number,
	) {
	}
}

export enum AlertType {
	success = 'success',
	warn = 'warn',
	danger = 'danger',
	info = 'info'
}
